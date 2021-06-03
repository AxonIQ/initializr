<script>
import Typography from "../Typography/Typography.svelte";

</script>
<div class="colors__row">
    <div class="color color--ox">
        <Typography weight="bold" color="dove">Ox - #21252B</Typography>
    </div>
    <div class="color color--rhino">
        <Typography weight="bold" color="dove">Rhino - #434A56</Typography>
    </div>
    <div class="color color--elephant">
        <Typography weight="bold">Elephant - #A1A4AA</Typography>
    </div>
    <div class="color color--seal">
        <Typography weight="bold">Seal - #F5F6F6</Typography>
    </div>
    <div class="color color--dove">
        <Typography weight="bold">Dove - #FFFFFF</Typography>
    </div>
    <div class="color color--fox">
        <Typography weight="bold" color="dove">Fox - #F35C00</Typography>
    </div>
    <div class="color color--peacock">
        <Typography weight="bold" color="dove">Peacock - #026CAF</Typography>
    </div>
</div>

<style lang="scss">
    @use "./colors.scss";
    
    .colors__row {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }
    .color {
        width: 200px;
        height: 200px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-right: 20px;
    }
    .color--ox {
        color: colors.$dove;
        background-color: colors.$ox
    }
    .color--rhino {
        color: colors.$dove;
        background-color: colors.$rhino
    }
    .color--elephant {
        color: colors.$dove;
        background-color: colors.$elephant
    }
    .color--seal {
        color: colors.$rhino;
        background-color: colors.$seal
    }
    .color--dove {
        color: colors.$ox;
        background-color: colors.$dove
    }
    .color--fox {
        color: colors.$dove;
        background-color: colors.$fox
    }
    .color--peacock {
        color: colors.$dove;
        background-color: colors.$peacock
    }

</style>