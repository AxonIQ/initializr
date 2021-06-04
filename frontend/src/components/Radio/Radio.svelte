<script lang="ts">
import Typography from "../Typography/Typography.svelte";

    export let value: string;
    export let label = '';
    export let group: string;
    export let name: string;
</script>
<div>
    <!-- svelte-ignore a11y-label-has-associated-control -->
    <label class="radio">
        <span class="radio__input">
          <input type="radio" value={value} name={name} bind:group={group} />
          <span class="radio__control"></span>
        </span>
        <span class="radio__label">
            <Typography size="s" color="rhino" inline>{label}</Typography>
        </span>
      </label>
</div>
<style lang="scss">
    @use "../Colors/colors.scss";

    // Heavily relied on https://moderncss.dev/pure-css-custom-styled-radio-buttons/ to create the custom radio buttons.
    *,
    *:before,
    *:after {
        box-sizing: border-box;
    }
    .radio {
        font-size: 14px;
        color: colors.$rhino;
        cursor: pointer;

        display: grid;
        grid-template-columns: min-content auto;
        grid-gap: 10px;
        align-items: baseline;
    }
    .radio__input {
        display: flex;
        input + .radio__control::before {
            content: "";
            width: 10px;
            height: 10px;
            box-shadow: inset 10px 10px colors.$rhino;
            border-radius: 50%;
            transition: 180ms transform ease-in-out;
            transform: scale(0);
        }
        input {
            opacity: 0;
            width: 0;
            height: 0;
            &:checked + .radio__control::before {
                transform: scale(1);
            }
            &:focus-visible + .radio__control {
                box-shadow: 0 0 0 2px #fff, 0 0 4px 3px colors.$rhino;
            }
        }
    }
    .radio__control {
        display: grid;
        place-items: center;
        width: 16px;
        height: 16px;
        border-radius: 50%;
        border: 2px solid colors.$rhino;
    }
    .radio__label {
        line-height: 1;
    }
</style>