<script lang="ts">
    import Typography from '../Typography/Typography.svelte';
    import Card from "../Card/Card.svelte";
import IconButtonPlus from '../IconButtonPlus/IconButtonPlus.svelte';
import IconButtonTrash from '../IconButtonTrash/IconButtonTrash.svelte';
</script>
<div>
    <div class="home-dependencies__heading">
        <Typography size="xl" weight="bold" removeMargin>Dependencies</Typography>
        <IconButtonPlus onClick={() => alert('Add dependencies')}/>
    </div>
    <div class="home-dependencies__item-wrapper">
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <Typography weight="bold">Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonTrash disabled />
                </div>
            </div>
        </Card>
    </div>
    <div class="home-dependencies__item-wrapper">
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <Typography weight="bold">Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonTrash />
                </div>
            </div>
        </Card>
    </div>
    <div class="home-dependencies__item-wrapper">
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <Typography weight="bold">Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonTrash />
                </div>
            </div>
        </Card>
    </div>
    <div class="home-dependencies__item-wrapper">
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <Typography weight="bold">Title</Typography>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
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
    .home-dependencies__item-wrapper {
        margin-bottom: 10px;
    }
    .home-dependencies__item {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .home-dependencies__item-text {
        margin-right: 10px;
    }
</style>