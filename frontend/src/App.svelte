<script lang="ts">
import BaseButton from "./components/BaseButton/BaseButton.svelte";
import Typography from "./components/Typography/Typography.svelte";
</script>

<main>
	<Typography size="s">Hello from typography</Typography>
	<Typography size="m">Hello from typography</Typography>
	<Typography size="l">Hello from typography</Typography>
	<div style="display: flex; justify-content: center;">
		<BaseButton text="Download" />
		<BaseButton variant="secondary" text="Preview" />
		<BaseButton variant="secondary" text="Share"/>
	</div>
</main>

<style type="scss">
	main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}
</style>