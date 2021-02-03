const defaultTheme = require("tailwindcss/defaultTheme");
module.exports = {
  purge: {
    enabled: process.env.PURGE_CSS === "production" ? true : false,
    content: ["./src/**/*.{js,jsx,ts,tsx}", "./public/index.html"],
  },
  darkMode: false, // or 'media' or 'class'
  theme: {
    screens: {
      xs: "475px",
      ...defaultTheme.screens,
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
