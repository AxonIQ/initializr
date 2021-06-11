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
    <div class="home-add-dependency-dialog__top-bar">
        <Typography size="xl" weight="bold">Add dependencies</Typography>
        
        <div class="home-add-dependency-dialog__search-input">
            <Input placeholder="Web, Security, JPA, Actuator, Devtools..." />
            <Button text="Search"/>
            <IconButtonRefresh />
        </div>
    </div>

    <ul class="home-add-dependency-dialog__group-list">
        {#each dependencyData.values as dependencyDataItem (dependencyDataItem.name) }
            <li class="home-add-dependency-dialog__group-item">
                <Typography weight="bold">{dependencyDataItem.name}</Typography>
                <ul class="home-add-dependency-dialog__item-list">
                    {#each dependencyDataItem.values as dependencyItem (dependencyItem.id)}
                        <li>
                            <Card>
                                <div class="home-add-dependency-dialog__item-card">
                                    <Typography weight="bold">{dependencyItem.name}</Typography>
                                    <Typography size="s">
                                        {dependencyItem.description}
                                    </Typography>
                                    <div class="home-add-dependency-dialog__add-button">
                                        <IconButtonPlus />
                                    </div>
                                </div>
                            </Card>
                        </li>
                    {/each}
                </ul>
            </li>
        {/each}
    </ul>
</Dialog>

<style lang="scss">
    @use "../Colors/colors.scss";
    
    .home-add-dependency-dialog__top-bar {
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
    .home-add-dependency-dialog__group-list {
        margin-top: 24px;
        display: flex;
        flex-direction: column;
        gap: 24px;
        overflow: auto;
        max-height: 640px;
    }
    .home-add-dependency-dialog__group-item {
        display: flex;
        flex-direction: column;
        gap: 10px;
        margin-left: 1px;
        margin-right: 1px;
        margin-bottom: 1px;
        &:not(:last-of-type) {
            margin-bottom: 18px;
        }
    }
    .home-add-dependency-dialog__item-list {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    .home-add-dependency-dialog__item-card {
        display: grid;
        grid-gap: 10px;
    }
    .home-add-dependency-dialog__add-button {
        grid-column: 2;
        grid-row: 1 / span 2;
        align-self: center;
        justify-self: end;
    }
</style>