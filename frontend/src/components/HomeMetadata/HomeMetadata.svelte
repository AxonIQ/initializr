<script lang="ts">
    import Typography from "../Typography/Typography.svelte";
    import Card from "../Card/Card.svelte";
    import Input from "../Input/Input.svelte";

    export let groupId = '';
    export let artifactId = '';
    export let name = '';
    export let description = '';
    export let packageName = '';
</script>

<div class="home-metadata">
    <div class="home-metadata__heading">
        <Typography size="xl" weight="bold">Application metadata</Typography>
    </div>
    <Card>
        <div class="home-metadata__items">
            
            <Typography size="s">GroupID</Typography>
            <Input placeholder="com.example" bind:value={groupId} />
            
            <Typography size="s">ArtifactID</Typography>
            <Input placeholder="Axon Demo" bind:value={artifactId}/>
            
            <Typography size="s">Name</Typography>
            <Input placeholder="Axon Demo" bind:value={name}/>
            
            <Typography size="s">Description</Typography>
            <Input placeholder="Demo project for Axon" bind:value={description} />
            
            <Typography size="s">Package</Typography>
            <Input placeholder="com.example.axon" bind:value={packageName}/>
    </div>
    </Card>
</div>

<style lang="scss">
    .home-metadata__heading {
        display: flex;
        align-items: center;
        height: 48px;
        margin-bottom: 10px;
    }
    .home-metadata {
        margin-bottom: 32px;
    }
    .home-metadata__items {
        display: grid;
        grid-template-columns: 1fr auto;
        grid-auto-flow: row;
        grid-gap: 24px;

        align-items: center;
        justify-content: center;
    }
</style>