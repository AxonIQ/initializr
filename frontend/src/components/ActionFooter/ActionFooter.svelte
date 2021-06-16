<script lang="ts">
import { userSelection } from "../../userSelectionStore";

import ActionFooterShareDialog from "../ActionFooterShareDialog/ActionFooterShareDialog.svelte";
import Button from "../Button/Button.svelte";

let shareVisible = false;

const defaultHeaders = new Headers();
defaultHeaders.append("Content-Type", "application/zip");
defaultHeaders.append("Content-Disposition", "attachment");

async function download() {
    const fileLink = document.createElement('a');
    fileLink.href = `http://localhost:8080/starter.zip?${new URLSearchParams($userSelection).toString()}`;
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