<script lang="ts">
import Typography from '../Typography/Typography.svelte';
import Card from "../Card/Card.svelte";
import Dialog from "../Dialog/Dialog.svelte";
import Input from "../Input/Input.svelte";
import Button from "../Button/Button.svelte";
import IconButtonPlus from '../IconButtonPlus/IconButtonPlus.svelte';
import IconButtonTrash from '../IconButtonTrash/IconButtonTrash.svelte';

let addDependenciesDialogVisible = false;

</script>
<div>
    <div class="home-dependencies__heading">
        <Typography size="xl" weight="bold">Dependencies</Typography>
        <IconButtonPlus onClick={() => addDependenciesDialogVisible = true}/>

        <Dialog
            open={addDependenciesDialogVisible}
            onClose={() => addDependenciesDialogVisible = false}
        >
            <div style="margin-bottom: 24px">
                <Typography size="xl" weight="bold">Add dependancies</Typography>
            </div>
            <div style="display: flex; align-items: center; margin-bottom: 24px">
                <div style="margin-right: 10px; width: 100%">
                    <Input placeholder="Web, Security, JPA, Actuator, Devtools..." />
                </div>
                <Button text="Search"/>
            </div>
            <div style="display: flex; align-items: center;">
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
                            <IconButtonPlus />
                        </div>
                    </div>
                </Card>
            </div>
        </Dialog>
    </div>
    <div class="home-dependencies__item-wrapper">
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
    </div>
    <div class="home-dependencies__item-wrapper">
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
    <div class="home-dependencies__item-wrapper">
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
    <div class="home-dependencies__item-wrapper">
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
    .home-depenencies__item-title {
        margin-bottom: 10px;
    }
</style>