<script lang="ts">
import type { DataDependencies,DependenciesValue } from "../../dataTypes";
import Card from "../Card/Card.svelte";
import HomeAddDependancyDialog from '../HomeAddDependencyDialog/HomeAddDependancyDialog.svelte';
import IconButtonPlus from '../IconButtonPlus/IconButtonPlus.svelte';
import IconButtonTrash from '../IconButtonTrash/IconButtonTrash.svelte';
import Typography from '../Typography/Typography.svelte';

export let dependencyData: DataDependencies;

let addDependenciesVisible = false;
let addedDependencies: DependenciesValue[] = [];

export let addedDependencyIds: string[] = [];
$: addedDependencyIds = addedDependencies.map(dep => dep.id);

</script>
<div class="home-dependencies__heading">
    <Typography size="xl" weight="bold">Dependencies</Typography>
    <IconButtonPlus onClick={() => addDependenciesVisible = true}/>

    <HomeAddDependancyDialog
        {dependencyData}
        {addedDependencies}
        onAddDependency={(dependencyItem) => {addedDependencies = [...addedDependencies, dependencyItem]}}
        bind:visible={addDependenciesVisible}
    />
</div>
<ul class="home-dependencies__list">
    {#each addedDependencies as addedDep (addedDep.id)}
        <li>
            <Card>
                <div class="home-dependencies__item">
                    <Typography weight="bold">{addedDep.name}</Typography>
                    <Typography size="s">
                        {addedDep.description}
                    </Typography>
                    <div class="home-dependencies__remove-button">
                        <IconButtonTrash onClick={() => {
                            addedDependencies = addedDependencies.filter(dep => dep.id !== addedDep.id);
                        }}/>
                    </div>
                </div>
            </Card>
        </li>
    {/each}
</ul>

<style lang="scss">
    .home-dependencies__heading {
        margin-bottom: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .home-dependencies__list {
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