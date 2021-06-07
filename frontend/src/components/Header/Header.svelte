<script>
import GradientDivider from "../GradientDivider/GradientDivider.svelte";
import IconMoon from "../IconMoon/IconMoon.svelte";
import IconSun from "../IconSun/IconSun.svelte";
import Toggle from "../Toggle/Toggle.svelte";

</script>

    <div class="header">
        <div class="header__content">
            <img src="/assets/header-logo.png" alt="Logo" />
            <Toggle>
                <IconSun slot="off-label" />
                <IconMoon slot="on-label" />
            </Toggle>
        </div>
    </div>
    <GradientDivider />

<style lang="scss">
    @use "../Colors/colors.scss";

    .header {
        background-color: colors.$dove;
        display: flex;
        justify-content: center;
        padding: 10px 20px;
    }
    .header__content {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        max-width: 1024px;
    }
</style>