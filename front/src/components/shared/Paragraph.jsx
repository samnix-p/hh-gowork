import React from 'react';
import styled from 'styled-components';

import { List, ListItem, Typography } from '@material-ui/core';

const TypographyListHeader = styled(Typography)`
    font-weight: 600;
    font-family: Roboto, sans-serif;
`;

const ListItemLink = (props) => <ListItem button component="a" {...props} />;

const Paragraph = (props) => (
    <List>
        <ListItemLink>
            <TypographyListHeader variant="h5">{props.value.header}</TypographyListHeader>
        </ListItemLink>
        {props.value.links.map((item, i) => (
            <ListItemLink key={item}>
                <Typography>
                    {props.value.id}.{i + 1} {item}
                </Typography>
            </ListItemLink>
        ))}
    </List>
);

export default Paragraph;
