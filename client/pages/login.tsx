import { NextPage } from 'next';
import Head from 'next/head';
import { useRouter } from 'next/router';
import { IoLogoFacebook, IoLogoTwitter } from 'react-icons/io';
import { Block } from 'baseui/block';
import { Button } from 'baseui/button';
import Container from '../components/UiElements/Container/Container';
import { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import {userService} from "services/user.service";

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

  function onSubmit({ username, password }) {
    return userService.login(username, password)
        .then(() => {
          const returnUrl = router.query.returnUrl || '/';
          // get return url from query parameters or default to '/'
          // @ts-ignore
          router.push(returnUrl);
        })
        .catch(error => {
          // console.log(error);
          setError('apierror',{message:error});
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
            Log in to INST.
          </Block>

          {/*<Button*/}
          {/*  onClick={() => router.push('/')}*/}
          {/*  startEnhancer={() => <IoLogoFacebook size="1.25rem" />}*/}
          {/*  overrides={{*/}
          {/*    BaseButton: {*/}
          {/*      style: ({ $theme }) => {*/}
          {/*        return {*/}
          {/*          ...$theme.typography.font250,*/}
          {/*          width: '100%',*/}
          {/*          maxWidth: '260px',*/}
          {/*          borderTopLeftRadius: '4px',*/}
          {/*          borderTopRightRadius: '4px',*/}
          {/*          borderBottomLeftRadius: '4px',*/}
          {/*          borderBottomRightRadius: '4px',*/}
          {/*          marginTop: '10px',*/}
          {/*          justifyContent: 'flex-start',*/}
          {/*          paddingLeft: '25px',*/}
          {/*          paddingRight: '25px',*/}
          {/*          backgroundColor: '#4267B2',*/}
          {/*          transition: 'all 0.3s ease',*/}
          {/*          ':hover': {*/}
          {/*            backgroundColor: '#4267B2',*/}
          {/*            opacity: 0.95,*/}
          {/*          },*/}
          {/*        };*/}
          {/*      },*/}
          {/*    },*/}
          {/*  }}*/}
          {/*>*/}
          {/*  Continue with Facebook*/}
          {/*</Button>*/}

          <div className="card">
            <h4 className="card-header">Next.js JWT Login Example</h4>
            <div className="card-body">
              <form onSubmit={handleSubmit(onSubmit)}>
                <div className="form-group">
                  <label>Username</label>
                  <input name="username" type="text" {...register('username')} className={`form-control ${errors.username ? 'is-invalid' : ''}`} />
                  <div className="invalid-feedback">{errors.username?.message}</div>
                </div>
                <div className="form-group">
                  <label>Password</label>
                  <input name="password" type="password" {...register('password')} className={`form-control ${errors.password ? 'is-invalid' : ''}`} />
                  <div className="invalid-feedback">{errors.password?.message}</div>
                </div>
                <Button
                  // startEnhancer={() => <IoL size="1.25rem" />}
                  overrides={{
                    BaseButton: {
                      style: ({ $theme }) => {
                        return {
                          ...$theme.typography.font250,
                          width: '100%',
                          maxWidth: '260px',
                          borderTopLeftRadius: '4px',
                          borderTopRightRadius: '4px',
                          borderBottomLeftRadius: '4px',
                          borderBottomRightRadius: '4px',
                          marginTop: '10px',
                          justifyContent: 'flex-start',
                          paddingLeft: '25px',
                          paddingRight: '25px',
                          backgroundColor: '#4267B2',
                          transition: 'all 0.3s ease',
                          ':hover': {
                            backgroundColor: '#4267B2',
                            opacity: 0.95,
                          },
                        };
                      },
                    },
                  }}
                  disabled={formState.isSubmitting}
                >
                  {formState.isSubmitting && <span className="spinner-border spinner-border-sm mr-1"/>}
                  Login
                </Button>
                {errors.apiError &&
                <div className="alert alert-danger mt-3 mb-0">{errors.apiError?.message}</div>
                }
              </form>
            </div>
          </div>
        </Block>
      </Container>
    </>
  );
};

export default Login;
