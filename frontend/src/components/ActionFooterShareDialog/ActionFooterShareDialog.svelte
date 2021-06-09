<script lang="ts">
    import Button from "../Button/Button.svelte";
    import Dialog from "../Dialog/Dialog.svelte";
    import Input from "../Input/Input.svelte";
    import Typography from '../Typography/Typography.svelte';
    
    export let visible = false;
</script>
    
    <Dialog
        open={visible}
        onClose={() => visible = false}
    >
        <div class="action-footer-share-dialog">
            <Typography size="xl" weight="bold">Share your configuration</Typography>
            <Typography size="s">Use this link to share the current configuration. Attributes can be removed from the URL if you want to rely on our defaults.</Typography>
            <div class="action-footer-share-dialog__input">
                <Input value="https://start.axoniq.io/#!type=maven-project&language=java&platformVersion=2.5.0.RELEASE&packaging=jar&jvmVersion=11&groupId=com.example&artifactId=demo&name=demo&description=Demo%20project%20for%20Axon&packageName=com.example.demo" />
                <Button text="Copy" />
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
