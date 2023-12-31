module.exports = {
  root: true,
  env: {browser: true, es2020: true},
  extends: [
    'eslint:recommended',
    'plugin:react/recommended',
    'plugin:react/jsx-runtime',
    'plugin:react-hooks/recommended',
  ],
  ignorePatterns: ['dist', '.eslintrc.cjs'],
  parserOptions: {ecmaVersion: 'latest', sourceType: 'module'},
  settings: {react: {version: '18.2'}},
  plugins: [
    'react-refresh',
  ],
  rules: {
    'react-refresh/only-export-components': [
      'warn',
      {allowConstantExport: true},
    ],
    'no-cond-assign': 'error',
    'react/prop-types': 0,
    'comma-dangle': 0,
    'space-before-function-paren': 0,
    'object-curly-spacing': 0,
    'no-trailing-spaces': 0,
    'eol-last': 0,
    'semi': 0,
    'quotes': 0,
    'indent': 0,
    'no-multiple-empty-lines': 0,
    'no-unused-vars': 0,
    'no-dupe-keys': 2,
    'padded-blocks': 0,
    'no-empty': 0,
    'react-hooks/exhaustive-deps' : 0,
  },
}
