<script lang="ts">
import Button from "../Button/Button.svelte";
import Card from "../Card/Card.svelte";
import Dialog from "../Dialog/Dialog.svelte";
import IconButtonPlus from '../IconButtonPlus/IconButtonPlus.svelte';
import Input from "../Input/Input.svelte";
import Typography from '../Typography/Typography.svelte';

export let visible = false;

</script>

<Dialog
    open={visible}
    onClose={() => visible = false}
>
    <div class="home-add-dependancy-dialog__title">
        <Typography size="xl" weight="bold">Add dependancies</Typography>
    </div>
    <div class="home-add-dependancy-dialog__search-wrapper">
        <div class="home-add-dependancy-dialog__search-input">
            <Input placeholder="Web, Security, JPA, Actuator, Devtools..." />
        </div>
        <Button text="Search"/>
    </div>
    <div class="home-add-dependancy-dialog__search-result">
        <Card>
            <div class="home-add-dependancy-dialog__search-result-item">
                <div class="home-add-dependancy-dialog__search-result-item-text">
                    <div class="home-add-dependancy-dialog__search-result-item-title">
                        <Typography weight="bold">Title</Typography>
                    </div>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonPlus />
                </div>
            </div>
        </Card>
        <Card>
            <div class="home-add-dependancy-dialog__search-result-item">
                <div class="home-add-dependancy-dialog__search-result-item-text">
                    <div class="home-add-dependancy-dialog__search-result-item-title">
                        <Typography weight="bold">Title</Typography>
                    </div>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonPlus />
                </div>
            </div>
        </Card>
    </div>
</Dialog>

<style lang="scss">
    .home-add-dependancy-dialog__title {
        margin-bottom: 24px;
    }
    .home-add-dependancy-dialog__search-wrapper {
        display: flex;
        align-items: center;
        margin-bottom: 24px;
    }
    .home-add-dependancy-dialog__search-input {
        margin-right: 10px;
        width: 100%;
    }
    .home-add-dependancy-dialog__search-result {
        display: grid;
        row-gap: 10px;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr;
    }
    .home-add-dependancy-dialog__search-result-item {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .home-add-dependancy-dialog__search-result-item-text {
        margin-right: 10px;
    }
    .home-add-dependancy-dialog__search-result-item-title {
        margin-bottom: 10px;
    }
</style>