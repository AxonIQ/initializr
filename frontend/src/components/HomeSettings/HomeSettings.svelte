<script lang="ts">
    import Typography from "../Typography/Typography.svelte";
    import Card from "../Card/Card.svelte";
    import Radio from "../Radio/Radio.svelte";

    let projectType = 'Maven';
    let projectLanguage = 'Java & Kotlin';
    let projectAxonVersion = 'Latest';
    let projectJavaVersion = '11';
</script>

<div>
    <div class="home-settings__heading">
        <Typography size="xl" weight="bold">Settings</Typography>
    </div>
    <Card>
        <div class="home-settings__items">
            
            <Typography removeMargin size="s">Project</Typography>
            <div>
                <Radio value="Maven" label="Maven" bind:group={projectType} name="projectType" />
				<Radio value="Gradle" label="Gradle" bind:group={projectType} name="projectType" />
            </div>

            <Typography removeMargin size="s">Language</Typography>
            <div>
                <Radio value="Java" label="Java" bind:group={projectLanguage} name="projectLanguage"/>
				<Radio value="Java & Kotlin" label="Java & Kotlin" bind:group={projectLanguage} name="projectLanguage"/>
            </div>

            <Typography removeMargin size="s">Axon</Typography>
            <div>
                <Radio value="Latest" label="Latest" bind:group={projectAxonVersion} name="projectAxonVersion"/>
				<Radio value="Snapshot" label="Snapshot" bind:group={projectAxonVersion} name="projectAxonVersion"/>
            </div>

            <Typography removeMargin size="s">Java</Typography>
            <div>
                <Radio value="16" label="16" bind:group={projectJavaVersion} name="projectJavaVersion"/>
				<Radio value="11" label="11" bind:group={projectJavaVersion} name="projectJavaVersion"/>
				<Radio value="8" label="8" bind:group={projectJavaVersion} name="projectJavaVersion"/>
            </div>
        </div>
    </Card>
</div>

<style lang="scss">
    .home-settings__heading {
        margin-bottom: 20px;
    }
    .home-settings__items {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-auto-flow: row;
        row-gap: 30px;

        justify-content: center;
    }
</style>