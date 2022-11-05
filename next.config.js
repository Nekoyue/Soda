module.exports = {
    reactStrictMode: true,
    swcMinify: true,
    distDir: 'build',
    images: {unoptimized: true},
    webpack(config) {
        config.module.rules.push({
            test: /\.svg$/i,
            issuer: /\.[jt]sx?$/,
            use: ['@svgr/webpack'],
        })

        return config
    },
}
