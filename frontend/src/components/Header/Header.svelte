<script>
import { isActionFooterVisible } from "../ActionFooter/actionFooterStore";
import GradientDivider from "../GradientDivider/GradientDivider.svelte";
import IconMoon from "../IconMoon/IconMoon.svelte";
import IconSun from "../IconSun/IconSun.svelte";
import Toggle from "../Toggle/Toggle.svelte";

let darkMode = false;

$: isActionFooterVisible.set(darkMode)

</script>

    <div class="header">
        <div class="header__content">
            <img src="/assets/header-logo.png" alt="Logo" />
            <Toggle bind:value={darkMode}>
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

        display: grid;
        grid-template-columns: minmax(20px, 1fr) minmax(auto, 1024px) minmax(20px, 1fr);
        background-color: colors.$dove;
        padding: 10px 0;
    }
    .header__content {
        grid-column: 2;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>