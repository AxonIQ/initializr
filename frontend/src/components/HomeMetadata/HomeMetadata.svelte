<script lang="ts">
    import Typography from "../Typography/Typography.svelte";
    import Card from "../Card/Card.svelte";
    import Input from "../Input/Input.svelte";
</script>

<div class="home-metadata">
    <div class="home-metadata__heading">
        <Typography size="xl" weight="bold">Application metadata</Typography>
    </div>
    <Card>
        <div class="home-metadata__items">
            
            <Typography size="s">GroupID</Typography>
            <Input placeholder="com.example" />
            
            <Typography size="s">ArtifactID</Typography>
            <Input placeholder="Axon Demo" />
            
            <Typography size="s">Name</Typography>
            <Input placeholder="Axon Demo" />
            
            <Typography size="s">Description</Typography>
            <Input placeholder="Demo project for Axon" />
            
            <Typography size="s">Package</Typography>
            <Input placeholder="com.example.axon" />
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