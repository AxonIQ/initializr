<script lang="ts">
    export let tag: "h1" | "h2" | "h3" | "h4" | "h5" | "h6" | "div" | "span" | "p" = 'div';
    export let size: 's' | 'm' | 'l' = 'm';
    export let weight: null | 'lighter' | 'light' | 'bold' = null;
    export let italic = false;
    export let uppercase = false;
    export let center = false;
    export let inline = false;
    export let removeMargin = false;

    $: classesToUse = [
        'typography',
        size ? `typography-size--${size}` : '',
        weight && `typography-weight--${weight}`,
        italic && `typography-weight--${italic}`,
        uppercase && `typography--uppercase`,
        center && `typography--center`,
        inline && `typography--inline`,
        !removeMargin && "typography--margin"
    ]
    // Filters out all the 'false' values.
    .filter(value => value)
    .join(' ');

</script>
{#if tag === 'div'}
    <div class={classesToUse}>
        <slot />
    </div>
{/if}
{#if tag === 'span'}
    <span class={classesToUse}>
        <slot />
    </span>
{/if}
{#if tag === 'h1'}
    <h1 class={classesToUse}>
        <slot />
    </h1>
{/if}
{#if tag === 'h2'}
    <h2 class={classesToUse}>
        <slot />
    </h2>
{/if}
{#if tag === 'h3'}
    <h3 class={classesToUse}>
        <slot />
    </h3>
{/if}
<style lang="scss">
    .typography {
        font-family: 'Lato', sans-serif;
        &.typography--margin {
            margin-bottom: 10px;
        }
        &.typography-size--s {
            font-size: 14px;
        }
        &.typography-size--m {
            font-size: 16px;
        }
        &.typography-size--l {
            font-size: 24px;
        }
        &.typography-weight--bold {
            font-weight: bold;
        }
        &.typography-weight--light {
            font-weight: 300;
        }
        &.typography-weight--lighter {
            font-weight: lighter;
        }
        &.typography--uppercase {
            text-transform: uppercase;
        }
        &.typography--italic {
            font-style: italic;
        }
        &.typography--center {
            text-align: center;
        }
        &.typography--inline {
            display: inline;
        }
    }
</style>