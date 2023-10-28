import { FC } from 'react';
import { Route, Navigate } from 'react-router-dom';
import autenticaStore from 'common/stores/authentica.store';

interface PrivateRouteProps {
  component: FC<any>;
  url: string
}

export const PrivateRoute: FC<PrivateRouteProps> = ({ url, component: Component, ...rest  }) => {
  const isAuthenticated = autenticaStore.isAuthenticated;

  return (
    <Route
    {...rest}
      path='' 
      Component={props =>
        isAuthenticated ? (
          <Component {...props} />
        ) : (
          <Navigate to="/login" />
        )
      }
    />
  );
};
