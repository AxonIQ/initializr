<script lang="ts">
import Typography from "../Typography/Typography.svelte";

    export let disabled = false;
    export let variant: "primary" | "secondary" = "primary";
    export let text: string;
    export let onClick: () => void = () => {};

    $: classesToUse = [
        'button',
        `button--${variant}`,
        disabled && 'button--disabled',
    ]
    .filter(value => value)
    .join(' ');
</script>

<button
    class={classesToUse}
    disabled={disabled}
    on:click={() => onClick()}>
    {#if text}
        <Typography
            center
            removeMargin
            color={variant === 'primary' ? 'dove' : 'rhino'}
            size="m"
            weight="bold">
            {#if disabled}
                <span class="button__text--disabled">{text}</span>
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

    .button {
        height: 36px;
        min-width: 130px;
        border-radius: 50px;
        background: colors.$seal;
        border: 0;
        filter: drop-shadow(0px 1px 2px rgba(0, 0, 0, 0.15));
        &:hover {
            cursor: pointer;
            opacity: 0.75;
            filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.1));
        }
        &:active {
            background-color: inherit;
            color: inherit;
            opacity: 0.5;
            filter: drop-shadow(0px 5px 3px rgba(0, 0, 0, 0.1));
        }
        &:focus-visible {
            box-shadow: 0 0 0 2px #fff, 0 0 4px 3px colors.$rhino;
            outline: 0;
        }
    }

    .button--primary {
        background-color: colors.$peacock;
        color: colors.$dove;
        &:hover {
            background-color: colors.$peacock;
            color: colors.$dove;
        }
        &:active {
            background-color: colors.$peacock;
            color: colors.$dove;
            
        }
    }
    .button--secondary {
        background-color: colors.$dove;
        color: colors.$rhino;
        &:hover {
            background-color: colors.$dove;
            color: colors.$rhino;
        }
        &:active {
            background-color: colors.$dove;
            color: colors.$rhino;
        }
    }
    .button--disabled {
        opacity: 0.5;
        pointer-events: none;
        &:hover {
            cursor: default
        }
    }
    .button__text--disabled {
        opacity: 0.5
    }
</style>