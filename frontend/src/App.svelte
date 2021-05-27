<script lang="ts">
import BaseButton from "./components/BaseButton/BaseButton.svelte";
import Colors from "./components/Colors/Colors.svelte";
import Typography from "./components/Typography/Typography.svelte";
</script>

<main>
	<div class="app_row">
		<Colors />
	</div>

	<div class="app_row">
		<hr />
		<Typography size="s">Hello from typography</Typography>
		<Typography size="s" weight="bold">Hello from typography</Typography>
		<Typography size="s" italic>Hello from typography</Typography>
		<hr />
		<Typography size="m">Hello from typography</Typography>
		<Typography size="m" weight="bold">Hello from typography</Typography>
		<Typography size="m" italic>Hello from typography</Typography>
		<hr />
		<Typography size="l">Hello from typography</Typography>
		<Typography size="l" weight="bold">Hello from typography</Typography>
		<Typography size="l" italic>Hello from typography</Typography>
		<hr />
	</div>

	<div class="app_row" style="display: flex; justify-content: center;">
		<div class="app__button-wrapper">
			<BaseButton variant="secondary" text="Use cases" />
		</div>
		<div class="app__button-wrapper">
			<BaseButton variant="secondary" disabled text="Use cases" />
		</div>
	</div>
	<div class="app_row" style="display: flex; justify-content: center;">
		<div class="app__button-wrapper">
			<BaseButton text="Use cases" />
		</div>
		<div class="app__button-wrapper">
			<BaseButton text="Use cases" disabled />
		</div>
	</div>
</main>

<style type="scss">
	main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}
	.app_row {
		margin-bottom: 20px;
	}
	.app__button-wrapper {
		margin-right: 20px;
	}
</style>