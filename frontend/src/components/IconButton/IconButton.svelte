<script lang="ts">
    export let color: "fox" | "peacock" | "seal" = "fox";
    export let onClick: () => void = () => {};
    export let disabled = false;
    export let size: "s" | " m" = "m";

    $: classesToUse = [
        'icon-button',
        `icon-button--${color}`,
        `icon-button--${size}`,
        disabled && 'icon-button--disabled',
    ]
    .filter(value => value)
    .join(' ');
</script>

<button
    class={classesToUse}
    disabled={disabled}
    on:click={() => onClick()}>
    <span class={disabled ? "icon-button__content--disabled" : ''}>
        <slot />
    </span>
</button>

<style lang="scss">
    @use "../Colors/colors.scss";

    .icon-button {
        padding: 0;

        display: flex;
        align-items: center;
        justify-content: center;
        border: 0;
        filter: drop-shadow(0px 1px 2px rgba(0, 0, 0, 0.15));
        &:hover {
            cursor: pointer;
            filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.1));
            opacity: 0.75;
        }
        &:active {
            background-color: inherit;
            color: inherit;
            filter: drop-shadow(0px 5px 3px rgba(0, 0, 0, 0.1));
            opacity: 0.5;
        }
        &:focus-visible {
            box-shadow: 0 0 0 2px #fff, 0 0 4px 3px colors.$rhino;
            outline: 0;
        }

        border-radius: 50px;
        background-color: colors.$fox;
        cursor: pointer;
    }
    .icon-button--m {
        min-width: 48px;
        min-height: 48px;
    }
    .icon-button--s {
        min-width: 36px;
        min-height: 36px;
    }
    .icon-button--fox {
        background-color: colors.$fox;
        &:hover {
            background-color: colors.$fox;
        }
        &:active {
            background-color: colors.$fox;
        }
    }
    .icon-button--peacock {
        background-color: colors.$peacock;
        &:hover {
            background-color: colors.$peacock;
        }
        &:active {
            background-color: colors.$peacock;
        }
    }
    .icon-button--seal {
        background-color: colors.$seal;
        &:hover {
            background-color: colors.$seal;
        }
        &:active {
            background-color: colors.$seal;
        }
    }
    .icon-button--disabled {
        opacity: 0.5;
        pointer-events: none;
        &:hover {
            cursor: default
        }
    }
    .icon-button__content--disabled {
        opacity: 0.5
    }
</style>