<script lang="ts">
    export let value = false;
</script>

<div class="toggle__wrapper">
    <slot name="off-label"></slot>
    <label class="toggle">
        <input class="toggle__input" type="checkbox" bind:checked={value}>
        <span class="toggle__slider"></span>
    </label>
    <slot name="on-label"></slot>
</div>

<style lang="scss">
    @use "../Colors/colors.scss";

    .toggle__wrapper {
        display: flex;
        align-items: center;
    }
    
    /* The toggle - the box around the slider */
    .toggle {
        display: flex;
        margin-left: 10px;
        margin-right: 10px;
    }

    /* Hide default HTML checkbox */
    .toggle__input {
        opacity: 0;
        width: 0;
        height: 0;
        margin: 0;

        &:focus-visible + .toggle__slider {
            box-shadow: 0 0 0 2px colors.$dove, 0 0 4px 3px colors.$rhino;
        }

        &:checked + .toggle__slider:before {
            transform: translateX(12px);
        }
    }

    /* The slider */
    .toggle__slider {
        cursor: pointer;
        display: flex;
        padding: 2px;
        align-items: center;
        transition: transform .4s;

        // In Figma the width is 28px, and the height is 16px, because an outline is used instead of a border.
        // The border is 2px thick, so it needs at least 4px inside an element with box-sizing: border-box
        width: 32px;
        height: 20px;
        border: 2px solid colors.$rhino;
        border-radius: 32px;

        &::before {
            content: "";
            width: 12px;
            height: 12px;
            box-shadow: inset 10px 10px colors.$rhino;
            border-radius: 50%;
            transition: 180ms transform ease-in-out;
        }
    }
</style>