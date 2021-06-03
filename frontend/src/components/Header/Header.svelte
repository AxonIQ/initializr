<script>
import GradientDivider from "../GradientDivider/GradientDivider.svelte";

</script>
<div class="header__wrapper">
    <div class="header">
        <div class="header__content">
            <img src="/assets/header-logo.png" alt="Logo" />
            <div>Just prentend I am a toggle</div>
        </div>
    </div>
    <GradientDivider />
</div>
<style lang="scss">
    @use "../Colors/colors.scss";

    .header__wrapper {
        background-color: colors.$dove;
    }
    .header {
        display: flex;
        justify-content: center;
    }
    .header__content {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        max-width: 1024px;
    }
</style>