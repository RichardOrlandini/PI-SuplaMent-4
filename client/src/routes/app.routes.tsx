import { Route } from 'react-router-dom';


export const AppRoutes = () => (
  <>
    <Route path="/login" component={Login} />
    <Route path="/register" component={Register} />
  </>
);