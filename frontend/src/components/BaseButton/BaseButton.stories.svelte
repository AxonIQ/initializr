<script>
  import { Meta, Template, Story } from "@storybook/addon-svelte-csf";
import BaseButton from "./BaseButton.svelte";
</script>

<Meta
  title="Example/BaseButton"
  component={BaseButton}
  argTypes={{
    text: { control: "text" },
    disabled: { control: "boolean" },
    variant: {
      control: { type: "select", options: ["primary", "secondary"] },
    },
    onClick: { action: "onClick" },
  }}
/>

<Template let:args>
  <BaseButton {...args} on:click={args.onClick} />
</Template>

<Story
  name="Primary"
  args={{
    text: "Button",
  }}
/>

<Story
  name="Secondary"
  args={{
    text: "Button",
    variant: "secondary"
  }}
/>

<Story
  name="Disabled"
  args={{
    text: "Button",
    disabled: true,
  }}
/>
