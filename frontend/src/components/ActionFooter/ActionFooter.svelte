<script lang="ts">
import ActionFooterShareDialog from "../ActionFooterShareDialog/ActionFooterShareDialog.svelte";
import Button from "../Button/Button.svelte";

let shareVisible = false;

</script>
    <div class="action-footer">
        <Button text="Download" />
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