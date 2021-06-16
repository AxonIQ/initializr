<script lang="ts">
    import Typography from "../Typography/Typography.svelte";
    import Card from "../Card/Card.svelte";
    import Radio from "../Radio/Radio.svelte";
import type { DataType, SettingsItem } from "../../dataTypes";

    export let projectTypeData: DataType;
    export let projectTypeSelected = '';

    export let projectLanguageData: SettingsItem;
    export let projectLanguageSelected = '';
    
    export let projectJavaVersionData: SettingsItem;
    export let projectJavaVersionSelected = '';

    export let projectPackagingData: SettingsItem;
    export let projectPackagingSelected = '';
</script>

<div>
    <div class="home-settings__heading">
        <Typography size="xl" weight="bold">Settings</Typography>
    </div>
    <Card>
        <div class="home-settings__items">
            
            <Typography size="s">Project</Typography>
            <div class="home-settings__radio-wrapper">
                {#each projectTypeData.values as projectTypeItem (projectTypeItem.id) }
                    {#if projectTypeItem.tags.format === "project"}
                        <Radio
                            value={projectTypeItem.id}
                            label={projectTypeItem.name}
                            name="projectType"
                            bind:group={projectTypeSelected}
                        />
                    {/if}
                {/each}
            </div>

            <Typography size="s">Language</Typography>
            <div class="home-settings__radio-wrapper">
                {#each projectLanguageData.values as projectLanguageItem (projectLanguageItem.id) }
                    <Radio
                        value={projectLanguageItem.id}
                        label={projectLanguageItem.name}
                        name="projectLanguage"
                        bind:group={projectLanguageSelected}
                    />
                {/each}
            </div>

            <Typography size="s">Java</Typography>
            <div class="home-settings__radio-wrapper">
                {#each projectJavaVersionData.values as projectJavaVersionItem (projectJavaVersionItem.id) }
                    <Radio
                        value={projectJavaVersionItem.id}
                        label={projectJavaVersionItem.name}
                        name="projectJavaVersion"
                        bind:group={projectJavaVersionSelected}
                    />
                {/each}
            </div>

            <Typography size="s">Packaging</Typography>
            <div class="home-settings__radio-wrapper">
                {#each projectPackagingData.values as projectPackagingItem (projectPackagingItem.id) }
                    <Radio
                        value={projectPackagingItem.id}
                        label={projectPackagingItem.name}
                        name="projectPackaging"
                        bind:group={projectPackagingSelected}
                    />
                {/each}
            </div>
        </div>
    </Card>
</div>

<style lang="scss">
    .home-settings__heading {
        display: flex;
        align-items: center;
        height: 48px;
        margin-bottom: 10px;
    }
    .home-settings__items {
        display: grid;
        grid-template-columns: auto 1fr;
        grid-auto-flow: row;
        grid-gap: 24px;

        justify-content: center;
    }
    .home-settings__radio-wrapper {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
</style>