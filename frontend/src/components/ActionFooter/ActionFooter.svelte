<script lang="ts">
import ActionFooterShareDialog from "../ActionFooterShareDialog/ActionFooterShareDialog.svelte";
import Button from "../Button/Button.svelte";

let shareVisible = false;

const defaultHeaders = new Headers();
defaultHeaders.append("Content-Type", "application/zip");
defaultHeaders.append("Content-Disposition", "attachment");

async function download() {
    const response = await fetch(
        'http://localhost:8080/starter.zip',
        {
            method: 'POST',
            headers: defaultHeaders,
            body: JSON.stringify({
                applicationName: '',
                artifactId: '',
                // ???
                // baseDir: '',
                bootVersion: '',
                dependencies: [],
                description: '',
                groupId: '',
                javaVersion: '',
                language: '',
                name: '',
                packageName: '',
                packaging: '',
                type: '',
                version: ''
            }),
        }
    );
    response.headers.forEach(console.log);
    // console.log(response.headers.get('content-disposition'));
    const blob = await response.blob();
    const file = window.URL.createObjectURL(blob);
    
    const fileLink = document.createElement('a');
    fileLink.href = file;
    fileLink.download = 'axon';
    fileLink.click();
}

</script>
    <div class="action-footer">
        <Button
            text="Download"
            onClick={() => download()}
        />
        <Button
            text="Share"
            onClick={() => shareVisible = true}
            variant="secondary" />
    </div>
    <ActionFooterShareDialog bind:visible={shareVisible}/>

<style lang="scss">
    @use "../Colors/colors.scss";

    .action-footer {
        display: flex;
        align-items: center;
        gap: 40px;
    }
</style>