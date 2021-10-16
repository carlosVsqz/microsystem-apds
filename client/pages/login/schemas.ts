import { FormDataSchema, FormUISchema } from '../../containers/Formik/types';

export const dataSchemaL: FormDataSchema = {
  fields: {
    username: {
      type: 'text',
      label: 'User Name',
      required: true,
    },
    password: {
      type: 'password',
      label: 'Password',
      required: true,
    },
  },
};

export const uiSchemaL: FormUISchema = {
  sections: [
    {
      title: 'Login ',
      fields: [
        'username',
        'password',
      ],
    },
  ],
};
