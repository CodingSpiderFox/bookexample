import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './author.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const AuthorDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const authorEntity = useAppSelector(state => state.author.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="authorDetailsHeading">
          <Translate contentKey="constraintissueApp.author.detail.title">Author</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{authorEntity.id}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="constraintissueApp.author.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{authorEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="constraintissueApp.author.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{authorEntity.lastName}</dd>
          <dt>
            <span id="birthTimestamp">
              <Translate contentKey="constraintissueApp.author.birthTimestamp">Birth Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {authorEntity.birthTimestamp ? <TextFormat value={authorEntity.birthTimestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/author" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/author/${authorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AuthorDetail;
