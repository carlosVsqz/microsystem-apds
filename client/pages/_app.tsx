import React from 'react';
import { AppProps } from 'next/app';
import { Provider as StyletronProvider } from 'styletron-react';
import { BaseProvider, LightTheme, DarkTheme } from 'baseui';
import { styletron, debug } from 'styletron';
import Layout from 'components/Layout/Layout';
import { ThemeSwitcherProvider, THEME } from 'contexts/theme/theme.provider';
import { CartProvider } from 'contexts/cart/cart.provider';
import { ApolloProvider } from '@apollo/client';
import { useApollo } from 'apollo/github.client';

import Head from 'next/head';
import { useRouter } from 'next/router';
import { useState, useEffect } from 'react';
import { userService } from 'services/user.service';
// external css
import '@glidejs/glide/dist/css/glide.core.min.css';
import 'react-toastify/dist/ReactToastify.css';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import 'assets/css/reset.css';
import 'react-flexbox-grid/dist/react-flexbox-grid.css';
import 'react-big-calendar/lib/addons/dragAndDrop/styles.css';
import 'typeface-open-sans';
export default function CustomApp({ Component, pageProps }: AppProps) {
  const router = useRouter();
  const [theme, setTheme] = React.useState(THEME.light);
  const [authorized, setAuthorized] = useState(false);
  // React.useEffect(() => {
  //   let SAVED_THEME = localStorage.getItem('theme');
  //   if (SAVED_THEME === null) {
  //     SAVED_THEME = THEME.light;
  //   }
  //   setTheme(SAVED_THEME);
  // }, []);
  useEffect(() => {
      let SAVED_THEME = localStorage.getItem('theme');
      if (SAVED_THEME === null) {
        SAVED_THEME = THEME.light;
      }
      setTheme(SAVED_THEME);
    // run auth check on initial load
    authCheck(router.asPath);

    // set authorized to false to hide page content while changing routes
    const hideContent = () => setAuthorized(false);
    router.events.on('routeChangeStart', hideContent);

    // run auth check on route change
    router.events.on('routeChangeComplete', authCheck)

    // unsubscribe from events in useEffect return function
    return () => {
      router.events.off('routeChangeStart', hideContent);
      router.events.off('routeChangeComplete', authCheck);
    }
  }, []);

  function authCheck(url) {
    // redirect to login page if accessing a private page and not logged in
    const publicPaths = ['/login'];
    const path = url.split('?')[0];
    if (!userService.userValue && !publicPaths.includes(path)) {
      setAuthorized(false);
      router.push({
        pathname: '/login',
        query: { returnUrl: router.asPath }
      });
    } else {
      setAuthorized(true);
    }
  }
  const apolloGithubClient = useApollo(pageProps.initialApolloState);

  return (
    <ApolloProvider client={apolloGithubClient}>

      <ThemeSwitcherProvider value={{ theme, setTheme }}>
        <StyletronProvider value={styletron} debugAfterHydration>
          { authorized &&
          <BaseProvider
            theme={
              theme === THEME.light
                ? { ...LightTheme, direction: 'ltr' }
                : { ...DarkTheme, direction: 'ltr' }
            }
          >
            <CartProvider>
              <Layout>
                <Component {...pageProps} />
              </Layout>
            </CartProvider>
          </BaseProvider>
          }
        </StyletronProvider>
      </ThemeSwitcherProvider>

    </ApolloProvider>
  );
}
