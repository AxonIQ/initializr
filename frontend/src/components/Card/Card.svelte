<div class="card" >
    <slot />
</div>
<style lang="scss">
    @use "../Colors/colors.scss";

    .card {
        border-radius: 4px;
        box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.4), 0px 5px 4px rgba(0, 0, 0, 0.1);
        background-color: colors.$dove;
        padding: 24px;
    }
</style>