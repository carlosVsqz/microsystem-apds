import { NextPage } from 'next';
import Head from 'next/head';
import Router, { useRouter } from 'next/router';
import { Block } from 'baseui/block';
import { Button } from 'baseui/button';
import Container from '../components/UiElements/Container/Container';
import React, { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import {userService} from "services/user.service";
import {Cell, Grid} from "baseui/layout-grid";
import Form from "../containers/Formik/Form";
import {uiSchemaL, dataSchemaL} from "./login/schemas";

const Login: NextPage<{}> = () => {
  const router = useRouter();

  useEffect(() => {
    // redirect to home if already logged in
    if (userService.userValue) {
      router.push('/');
    }
  }, [] );
  const validationSchema = Yup.object().shape({
    username: Yup.string().required('Username is required'),
    password: Yup.string().required('Password is required')
  });
  const formOptions = { resolver: yupResolver(validationSchema) };

  // get functions to build form with useForm() hook
  const { register, handleSubmit, setError, formState } = useForm(formOptions);
  const { errors } = formState;

  function onSubmit({ username, password, }) {
    return userService.login(username, password)
        .then(() => {
          const returnUrl = router.query.returnUrl || '/';
          // get return url from query parameters or default to '/'
          // @ts-ignore
          router.push(returnUrl);
        })
        .catch(error => {
          // console.log(error);
          setError('Error',{message:error});
        });
  }

  return (
    <>
      <Head>
        <title>Login</title>
        <meta name="Description" content="Inst login page" />
      </Head>

      <Container>
        <Block
          overrides={{
            Block: {
              style: {
                minHeight: 'calc(100vh - 213px)',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
              },
            },
          }}
        >
          <Block
            as="h1"
            overrides={{
              Block: {
                style: ({ $theme }) => {
                  return {
                    ...$theme.typography.font1250,
                    fontWeight: 700,
                    marginBottom: '30px',
                  };
                },
              },
            }}
          >
            Log in to APDS.
          </Block>
          <Cell span={[12, 12, 6]}>
          <Form
              initialValues={{}}
              dataSchema={dataSchemaL}
              uiSchema={uiSchemaL}
              onSubmit={values => onSubmit(values)}
              // onSubmit={values => alert(JSON.stringify(values, null, 4))}
          />
              {errors.Error && errors.Error?.message}
          </Cell>
        </Block>
      </Container>
    </>
  );
};

export default Login;
