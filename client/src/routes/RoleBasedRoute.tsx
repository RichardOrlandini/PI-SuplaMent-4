import autenticaStore from 'common/stores/authentica.store';
import { FC, ReactNode } from 'react';
import { Route, Navigate } from 'react-router-dom';

interface RoleBasedRouteProps {
  path: string;
  component: FC<any>;
  role: string;
  children?: ReactNode;
}

export const RoleBasedRoute: FC<RoleBasedRouteProps> = ({ component: Component, role, ...rest }) => {
  const userRole = autenticaStore.user.role
  
  return (
    <Route
      {...rest}
      Component={props =>
        userRole === role ? (
          <Component {...props} />
        ) : (
          <Navigate to="/" />
        )
      }
    />
  );
};