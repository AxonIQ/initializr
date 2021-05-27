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
            weight="bold">
            {#if disabled}
                <span class="base-button__text--disabled">{text}</span>
            {:else}
                {text}
            {/if}
        </Typography>
    {:else}
        <slot />    
    {/if}
</button>
<style lang="scss">
    @use "../Colors/colors.scss";

    .base-button {
        height: 36px;
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
        }
    }

    .base-button--primary {
        background-color: colors.$peacock;
        color: colors.$dove;
        &:hover {
            background-color: colors.$peacock;
            color: colors.$dove;
            opacity: 0.75;
            filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.1));
        }
        &:active {
            background-color: colors.$peacock;
            color: colors.$dove;
            opacity: 0.5;
            filter: drop-shadow(0px 5px 3px rgba(0, 0, 0, 0.1));
        }
    }
    .base-button--secondary {
        background-color: colors.$dove;
        color: colors.$rhino;
        filter: drop-shadow(0px 1px 2px rgba(0, 0, 0, 0.15));
        &:hover {
            background-color: colors.$dove;
            color: colors.$rhino;
            filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.1));
        }
        &:active {
            background-color: colors.$dove;
            color: colors.$rhino;
            filter: drop-shadow(0px 5px 3px rgba(0, 0, 0, 0.1));
        }
    }
    .base-button--disabled {
        opacity: 0.5;
        pointer-events: none;
        &:hover {
            cursor: default
        }
    }
    .base-button__text--disabled {
        opacity: 0.5
    }
</style>