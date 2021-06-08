<script>
import { isActionFooterVisible } from "../ActionFooter/actionFooterStore";

import IconMoon from "../IconMoon/IconMoon.svelte";
import IconSun from "../IconSun/IconSun.svelte";
import Toggle from "../Toggle/Toggle.svelte";

let darkMode = false;

$: isActionFooterVisible.set(darkMode)

</script>

    <div class="header">
        <img src="/assets/header-logo.png" alt="Logo" />
        <Toggle bind:value={darkMode}>
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