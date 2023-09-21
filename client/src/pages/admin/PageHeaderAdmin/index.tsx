import { Box, Button, Typography, AppBar, Container, Toolbar, Link, Paper } from "@mui/material"
import { Link as RouterLink, Outlet } from 'react-router-dom'
import { Header } from "../../../components/Header"


export function PageHeaderAdmin() {

    return (
        <>
            <Header/>

            <AppBar position="static">

                <Container maxWidth="xl">
                    <Toolbar>
                        <Typography variant="h6" sx={{ marginRight: 10 }}>
                        <Link component={RouterLink} to="/">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Supla Adm
                                </Button>
                            </Link>
                        </Typography>

                        <Box sx={{ display: 'flex', flexGrow: 1 }}>

                            <Link component={RouterLink} to="/admin/usuarios">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Usuarios
                                </Button>
                            </Link>

                            <Link component={RouterLink} to="/admin/categorias">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Categorias
                                </Button>
                            </Link>


                            <Link component={RouterLink} to="/admin/produtos">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Produtos
                                </Button>
                            </Link>
                        </Box>
                    </Toolbar>
                </Container>
            </AppBar>
         <Box>
                <Container maxWidth="lg" sx={{ mt: 1 }}>
                    <Paper sx={{ p: 2 }}>
                        <Outlet  />
                    </Paper>
                </Container>

                
            </Box>
        </>
    )
}
