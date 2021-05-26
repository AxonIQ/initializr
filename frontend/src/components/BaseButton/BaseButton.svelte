<script lang="ts">
import Typography from "../Typography/Typography.svelte";

    export let disabled = false;
    export let variant: "primary" | "secondary" = "primary";
    export let text: string;

    $: classesToUse = [
        'base-button',
        `base-button--${variant}`,
        disabled && 'base-button--disabled',
    ]
    .filter(value => value)
    .join(' ');
</script>

<button
    class={classesToUse}
    disabled={disabled}>
    {#if text}
        <Typography
            center
            removeMargin
            size="m"
            weight="bold">{text}
        </Typography>
    {:else}
        <slot />    
    {/if}
</button>
<style lang="scss">
    @use "../Colors/colors.scss";

    .base-button {
        height: 48px;
        min-width: 130px;
        border-radius: 50px;
        background: colors.$seal;
        border: 0;
        filter: drop-shadow(0px 1px 2px rgba(0, 0, 0, 0.15));
        &:hover {
            cursor: pointer;
        }
        &:active {
            background-color: inherit;
            color: inherit;
            border: 1px solid colors.$ox;
        }
    }
    .base-button--primary {
        background-color: colors.$peacock;
        color: colors.$dove;
    }
    .base-button--disabled {
        opacity: 0.3;
        pointer-events: none;
        &:hover {
            cursor: default
        }
    }
</style>