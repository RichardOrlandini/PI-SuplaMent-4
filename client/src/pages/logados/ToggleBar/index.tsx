import { useState } from "react";
import { Drawer, List, ListItem, ListItemIcon, ListItemText, IconButton } from "@mui/material";
import { Menu as MenuIcon, Home as HomeIcon, ShoppingCart as ShoppingCartIcon } from "@mui/icons-material";
import { Footer } from "components/Footer";
import { Outlet } from "react-router-dom";
import { Link } from 'react-router-dom';

export default function ToggleBar() {
    const [open, setOpen] = useState(false);

    return (
        <div>
            <div>
                <IconButton edge="start" color="inherit" aria-label="menu" onClick={() => setOpen(true)}>
                    <MenuIcon />
                </IconButton>
            </div>

            <main>
                <Outlet />
            </main>
            <Drawer anchor="left" open={open} onClose={() => setOpen(false)}>
                <List>
                    <ListItem button component={Link} to="/">
                        <ListItemIcon>
                            <HomeIcon />
                        </ListItemIcon>
                        <ListItemText primary="Home" />
                    </ListItem>
                </List>
            </Drawer>
            <Footer />
        </div>
    );
}
