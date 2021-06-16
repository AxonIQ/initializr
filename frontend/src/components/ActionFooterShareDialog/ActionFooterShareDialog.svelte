<script lang="ts">
import { onMount } from "svelte";

    
    import { userSelection } from "../../userSelectionStore";
    import Button from "../Button/Button.svelte";
    import Dialog from "../Dialog/Dialog.svelte";
    import Input from "../Input/Input.svelte";
    import Typography from '../Typography/Typography.svelte';
    
    export let visible = false;
    $: shareableLink = `${location.origin}?${new URLSearchParams($userSelection).toString()}`
</script>
    
    <Dialog
        open={visible}
        onClose={() => visible = false}
    >
        <div class="action-footer-share-dialog">
            <Typography size="xl" weight="bold">Share your configuration</Typography>
            <Typography size="s">Use this link to share the current configuration. Attributes can be removed from the URL if you want to rely on our defaults.</Typography>
            <div class="action-footer-share-dialog__input">
                <Input
                    value={shareableLink} />
                <Button text="Copy" onClick={() => {
                    navigator.clipboard.writeText(shareableLink);
                }}/>
            </div>
        </div>
    </Dialog>
    
<style lang="scss">
    .action-footer-share-dialog {
        display: flex;
        flex-direction: column;
        gap: 24px;
    }
    .action-footer-share-dialog__input {
        display: flex;
        gap: 10px;
        align-items: center;
    }
</style>
