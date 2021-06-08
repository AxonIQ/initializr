<script lang="ts">
import Button from "../Button/Button.svelte";
</script>
    <div class="action-footer__download">
        <Button text="Download" />
    </div>
    <Button text="Share" variant="secondary" />

<style lang="scss">
    @use "../Colors/colors.scss";

    .action-footer__download {
        display: inline-block;
        margin-right: 40px;
    }
</style>