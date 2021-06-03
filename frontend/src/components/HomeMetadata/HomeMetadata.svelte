<script lang="ts">
    import Typography from "../Typography/Typography.svelte";
    import Card from "../Card/Card.svelte";
    import Input from "../Input/Input.svelte";
</script>

<div>
    <div class="home-metadata__heading">
        <Typography size="xl" weight="bold">Application metadata</Typography>
    </div>
    <Card>
        <div class="home-metadata__items">
            
            <Typography removeMargin size="s">GroupID</Typography>
            <Input placeholder="com.example" />
            
            <Typography removeMargin size="s">ArtifactID</Typography>
            <Input placeholder="Axon Demo" />
            
            <Typography removeMargin size="s">Name</Typography>
            <Input placeholder="Axon Demo" />
            
            <Typography removeMargin size="s">Description</Typography>
            <Input placeholder="Demo project for Axon" />
            
            <Typography removeMargin size="s">Package</Typography>
            <Input placeholder="com.example.axon" />
    </div>
    </Card>
</div>

<style lang="scss">
    .home-metadata__heading {
        margin-bottom: 20px;
    }
    .home-metadata__items {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-auto-flow: row;
        row-gap: 30px;

        align-items: center;
        justify-content: center;
    }
</style>