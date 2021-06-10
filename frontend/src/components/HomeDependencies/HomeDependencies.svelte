<script lang="ts">
import type { DataDependencies } from "../../dataTypes";

import Card from "../Card/Card.svelte";
import HomeAddDependancyDialog from '../HomeAddDependencyDialog/HomeAddDependancyDialog.svelte';
import IconButtonPlus from '../IconButtonPlus/IconButtonPlus.svelte';
import IconButtonTrash from '../IconButtonTrash/IconButtonTrash.svelte';
import Typography from '../Typography/Typography.svelte';

export let dependencyData: DataDependencies;

let addDependenciesVisible = false;

</script>
<div>
    <div class="home-dependencies__heading">
        <Typography size="xl" weight="bold">Dependencies</Typography>
        <IconButtonPlus onClick={() => addDependenciesVisible = true}/>

        <HomeAddDependancyDialog
            {dependencyData}
            bind:visible={addDependenciesVisible}
        />
    </div>
    <div class="home-dependencies">
        <Card>
            <div class="home-dependencies__item">
                <Typography weight="bold">Title</Typography>
                <Typography size="s">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                </Typography>
                <div class="home-dependencies__remove-button">
                    <IconButtonTrash disabled />
                </div>
            </div>
        </Card>
        <Card>
            <div class="home-dependencies__item">
                <Typography weight="bold">Title</Typography>
                <Typography size="s">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                </Typography>
                <div class="home-dependencies__remove-button">
                    <IconButtonTrash />
                </div>
            </div>
        </Card>
        <Card>
            <div class="home-dependencies__item">
                <Typography weight="bold">Title</Typography>
                <Typography size="s">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                </Typography>
                <div class="home-dependencies__remove-button">
                    <IconButtonTrash />
                </div>
            </div>
        </Card>
        <Card>
            <div class="home-dependencies__item">
                <Typography weight="bold">Title</Typography>
                <Typography size="s">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                </Typography>
                <div class="home-dependencies__remove-button">
                    <IconButtonTrash />
                </div>
            </div>
        </Card>
    </div>
</div>

<style lang="scss">
    .home-dependencies__heading {
        margin-bottom: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .home-dependencies {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr;
        row-gap: 10px;
    }
    .home-dependencies__item {
        display: grid;
        grid-template-columns: 1fr auto;
        grid-gap: 10px;
    }
    .home-dependencies__remove-button {
        grid-column: 2;
        grid-row: 1 / span 2;
        align-self: center;
    }
</style>