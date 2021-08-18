const { createActionAuth } = require("@octokit/auth-action");
const { Octokit } = require("@octokit/rest");
const fs = require('fs-extra');
const StreamZip = require('node-stream-zip');

const OWNER = 'AxonIQ';
const REPO = 'product-ui';
const ZIP = 'zip';
const TARGET_DIR = './target/classes/static';
const TARGET_FILE = './build.zip';

const auth = createActionAuth();
// const authentication = await auth();
const octokit = new Octokit({
    // TODO: for local development, you should provide a valid GitHub Personal Access Token
    auth: auth,
    userAgent: 'Download Artifact script',
});

async function getArtifacts () {
    console.log(auth);
    const response = await octokit.rest.actions.listArtifactsForRepo({
        owner: OWNER,
        repo: REPO
      });

    const initializrArtifact = response.data.artifacts.find(artifact => artifact.name === 'initializr');
    const file = await octokit.rest.actions.downloadArtifact({
        owner: OWNER,
        repo: REPO,
        artifact_id: initializrArtifact.id,
        archive_format: ZIP
    });
    await fs.writeFile(TARGET_FILE, Buffer.from(file.data));

    const zip = new StreamZip.async({file: TARGET_FILE});
    await fs.remove(TARGET_DIR);
    await fs.ensureDir(TARGET_DIR);
    await zip.extract(null, TARGET_DIR);
    await zip.close();

    await fs.remove(TARGET_FILE);
}

getArtifacts();
