<script lang="ts">
import Card from "../Card/Card.svelte";
import HomeAddDependancyDialog from '../HomeAddDependancyDialog/HomeAddDependancyDialog.svelte';
import IconButtonPlus from '../IconButtonPlus/IconButtonPlus.svelte';
import IconButtonTrash from '../IconButtonTrash/IconButtonTrash.svelte';
import Typography from '../Typography/Typography.svelte';

let addDependanciesVisible = false;

</script>
<div>
    <div class="home-dependencies__heading">
        <Typography size="xl" weight="bold">Dependencies</Typography>
        <IconButtonPlus onClick={() => addDependanciesVisible = true}/>

        <HomeAddDependancyDialog bind:visible={addDependanciesVisible}/>
    </div>
    <div class="home-dependencies">
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <div class="home-depenencies__item-title">
                        <Typography weight="bold">Title</Typography>
                    </div>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonTrash disabled />
                </div>
            </div>
        </Card>
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <div class="home-depenencies__item-title">
                        <Typography weight="bold">Title</Typography>
                    </div>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonTrash />
                </div>
            </div>
        </Card>
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <div class="home-depenencies__item-title">
                        <Typography weight="bold">Title</Typography>
                    </div>
                    <Typography size="s">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut pulvinar odio. Sed dictum mi felis, et rhoncus augue rutrum eu.
                    </Typography>
                </div>
                <div>
                    <IconButtonTrash />
                </div>
            </div>
        </Card>
        <Card>
            <div class="home-dependencies__item">
                <div class="home-dependencies__item-text">
                    <div class="home-depenencies__item-title">
                        <Typography weight="bold">Title</Typography>
                    </div>
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
    .home-dependencies {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr;
        row-gap: 10px;
    }
    .home-dependencies__item {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .home-dependencies__item-text {
        margin-right: 10px;
    }
    .home-depenencies__item-title {
        margin-bottom: 10px;
    }
</style>