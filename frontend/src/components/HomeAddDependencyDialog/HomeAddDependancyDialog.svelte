<script lang="ts">
import type { DataDependencies } from "../../dataTypes";

import Button from "../Button/Button.svelte";
import Card from "../Card/Card.svelte";
import Dialog from "../Dialog/Dialog.svelte";
import IconButtonRefresh from "../IconButonRefresh/IconButtonRefresh.svelte";
import IconButtonPlus from '../IconButtonPlus/IconButtonPlus.svelte';
import Input from "../Input/Input.svelte";
import Typography from '../Typography/Typography.svelte';

export let visible = false;
export let dependencyData: DataDependencies;

</script>

<Dialog
    open={visible}
    onClose={() => visible = false}
>
    <div class="home-add-dependency-dialog">
        <Typography size="xl" weight="bold">Add dependencies</Typography>
        
        <div class="home-add-dependency-dialog__search-input">
            <Input placeholder="Web, Security, JPA, Actuator, Devtools..." />
            <Button text="Search"/>
            <IconButtonRefresh />
        </div>

        <div class="home-add-dependency-dialog__search-result-group">
            <Typography weight="bold">Group Title</Typography>
            <Card>
                <div class="home-add-dependency-dialog__search-result-item">
                    <Typography weight="bold">Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                    <div class="home-add-dependency-dialog__add-button">
                        <IconButtonPlus />
                    </div>
                </div>
            </Card>
            <Card>
                <div class="home-add-dependency-dialog__search-result-item">
                    <Typography weight="bold">Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                    <div class="home-add-dependency-dialog__add-button">
                        <IconButtonPlus />
                    </div>
                </div>
            </Card>
        </div>
            
        <div class="home-add-dependency-dialog__search-result-group">
            <Typography weight="bold">Group Title</Typography>
            <Card>
                <div class="home-add-dependency-dialog__search-result-item">
                    <Typography weight="bold">Group Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                    <div class="home-add-dependency-dialog__add-button">
                        <IconButtonPlus />
                    </div>
                </div>
            </Card>
            <Card>
                <div class="home-add-dependency-dialog__search-result-item">
                    <Typography weight="bold">Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                    <div class="home-add-dependency-dialog__add-button">
                        <IconButtonPlus />
                    </div>
                </div>
            </Card>
        </div>
    </div>
</Dialog>

<style lang="scss">
    .home-add-dependency-dialog {
        display: flex;
        flex-direction: column;
        gap: 24px;
    }
    .home-add-dependency-dialog__search-input {
        display: flex;
        flex-shrink: 0;
        gap: 10px;
        align-items: center;

    }
    .home-add-dependency-dialog__search-result {
        display: flex;
        flex-direction: column;
    }
    .home-add-dependency-dialog__search-result-group {
        display: flex;
        flex-direction: column;
        gap: 10px;
        &:not(:last-of-type) {
            margin-bottom: 18px;
        }
    }
    .home-add-dependency-dialog__search-result-item {
        display: grid;
        grid-gap: 10px;
    }
    .home-add-dependency-dialog__add-button {
        grid-column: 2;
        grid-row: 1 / span 2;
        align-self: center;
    }
</style>