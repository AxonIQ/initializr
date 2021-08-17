require('dotenv').config();
const { Octokit } = require("@octokit/rest");
const fs = require('fs-extra');
const StreamZip = require('node-stream-zip');

const octokit = new Octokit({
    auth: process.env.PA_TOKEN_GITHUB,
    userAgent: 'Download Artifact script',
});

async function getArtifacts () {
    const response = await octokit.rest.actions.listArtifactsForRepo({
        owner: 'AxonIQ',
        repo: 'product-ui'
      });

    const initializrArtifcat = response.data.artifacts.find(artifact => artifact.name === 'initializr');
    const file = await octokit.rest.actions.downloadArtifact({
        owner: 'AxonIQ',
        repo: 'product-ui',
        artifact_id: initializrArtifcat.id,
        archive_format: 'zip'
    });
    await fs.writeFile('build.zip', Buffer.from(file.data));

    const zip = new StreamZip.async({file: './build.zip'});
    await fs.remove('./target/classes/static');
    await fs.ensureDir('./target/classes/static');
    await zip.extract(null, './target/classes/static');
    await zip.close();

    await fs.remove('./build.zip');
}

getArtifacts();
