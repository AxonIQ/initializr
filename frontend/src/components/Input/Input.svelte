<script lang="ts">
    export let placeholder = '';
    export let value = '';
    export let disabled = false;
</script>
<input
    class="input"
    disabled={disabled}
    placeholder={placeholder}
    bind:value={value}
/>
<style lang="scss">
    @use "../Colors/colors.scss";

    .input {
        height: 26px;
        border: 1px solid colors.$elephant;
        box-shadow: inset 0px 1px 2px rgba(0, 0, 0, 0.25);
        width: 100%;
        padding-left: 7px;
        box-sizing: border-box;

        &:focus-visible {
            outline: 0;
        }
    }
</style>