<script lang="ts">
    export let open: boolean;
    export let onClose: () => void;
</script>

<svelte:window on:keydown={(event) => {
    if (!open) {
        return;
    }

    if (event.key === 'Escape') {
        onClose();
    }
}} />

{#if open}
    <div
        class="dialog__overlay"
        on:click={onClose}
    >
        <div
            class="dialog"
            on:click={(event) => event.stopPropagation()}
        >
            <slot />
        </div>
    </div>
{/if}

<style lang="scss">
    @use '../Colors/colors.scss';

    .dialog__overlay {
        position: fixed;
        left: 0;
        top: 0;
        z-index: 1;

        width: 100vw;
        height: 100vh;
        background-color: rgba(0, 0, 0, 0.6);
        overflow: auto;

        display: flex;
        justify-content: center;
        align-items: center;
    }
    .dialog {
        width: 730px;
        border: 0;
        padding: 32px;
        background-color: colors.$seal;
        box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.4), 0px 5px 4px rgba(0, 0, 0, 0.1);
    }
</style>
