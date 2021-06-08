<script>
import IconMoon from "../IconMoon/IconMoon.svelte";
import IconSun from "../IconSun/IconSun.svelte";
import Toggle from "../Toggle/Toggle.svelte";

</script>

    <div class="header">
        <img src="/assets/header-logo.png" alt="Logo" />
        <Toggle>
            <IconSun slot="off-label" />
            <IconMoon slot="on-label" />
        </Toggle>
    </div>

<style lang="scss">
    @use "../Colors/colors.scss";

    .header {
        background-color: colors.$dove;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 0;
    }
</style>