interface UserModel extends Auth.UserInfo {
  token: string;
  refreshToken: string;
  password: string;
}

export const userModel: UserModel[] = [
  {
    token: '__TOKEN_SOYBEAN__',
    refreshToken: '__REFRESH_TOKEN_SOYBEAN__',
    id: '0',
    username: 'Soybean',
    userRole: 'super',
    password: 'soybean123'
  },
  {
    token: '__TOKEN_SUPER__',
    refreshToken: '__REFRESH_TOKEN_SUPER__',
    id: '1',
    username: 'Super',
    userRole: 'super',
    password: 'super123'
  },
  {
    token: '__TOKEN_ADMIN__',
    refreshToken: '__REFRESH_TOKEN_ADMIN__',
    id: '2',
    username: 'Admin',
    userRole: 'admin',
    password: 'admin123'
  },
  {
    token: '__TOKEN_USER01__',
    refreshToken: '__REFRESH_TOKEN_USER01__',
    id: '3',
    username: 'User01',
    userRole: 'user',
    password: 'user01123'
  }
];
