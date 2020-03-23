import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UsuariosComponentsPage, UsuariosDeleteDialog, UsuariosUpdatePage } from './usuarios.page-object';

const expect = chai.expect;

describe('Usuarios e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let usuariosComponentsPage: UsuariosComponentsPage;
  let usuariosUpdatePage: UsuariosUpdatePage;
  let usuariosDeleteDialog: UsuariosDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Usuarios', async () => {
    await navBarPage.goToEntity('usuarios');
    usuariosComponentsPage = new UsuariosComponentsPage();
    await browser.wait(ec.visibilityOf(usuariosComponentsPage.title), 5000);
    expect(await usuariosComponentsPage.getTitle()).to.eq('Usuarios');
    await browser.wait(ec.or(ec.visibilityOf(usuariosComponentsPage.entities), ec.visibilityOf(usuariosComponentsPage.noResult)), 1000);
  });

  it('should load create Usuarios page', async () => {
    await usuariosComponentsPage.clickOnCreateButton();
    usuariosUpdatePage = new UsuariosUpdatePage();
    expect(await usuariosUpdatePage.getPageTitle()).to.eq('Create or edit a Usuarios');
    await usuariosUpdatePage.cancel();
  });

  it('should create and save Usuarios', async () => {
    const nbButtonsBeforeCreate = await usuariosComponentsPage.countDeleteButtons();

    await usuariosComponentsPage.clickOnCreateButton();

    await promise.all([
      usuariosUpdatePage.setNombreInput('nombre'),
      usuariosUpdatePage.setApellidosInput('apellidos'),
      usuariosUpdatePage.setEmailInput('email'),
      usuariosUpdatePage.setUsuarioInput('M'),
      usuariosUpdatePage.setPasswordInput('a'),
      usuariosUpdatePage.setFechaRegistroInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      usuariosUpdatePage.setCiudadInput('ciudad'),
      usuariosUpdatePage.setUrlFotoPerfilInput('t8')
    ]);

    expect(await usuariosUpdatePage.getNombreInput()).to.eq('nombre', 'Expected Nombre value to be equals to nombre');
    expect(await usuariosUpdatePage.getApellidosInput()).to.eq('apellidos', 'Expected Apellidos value to be equals to apellidos');
    expect(await usuariosUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await usuariosUpdatePage.getUsuarioInput()).to.eq('M', 'Expected Usuario value to be equals to M');
    expect(await usuariosUpdatePage.getPasswordInput()).to.eq('a', 'Expected Password value to be equals to a');
    expect(await usuariosUpdatePage.getFechaRegistroInput()).to.contain(
      '2001-01-01T02:30',
      'Expected fechaRegistro value to be equals to 2000-12-31'
    );
    expect(await usuariosUpdatePage.getCiudadInput()).to.eq('ciudad', 'Expected Ciudad value to be equals to ciudad');
    expect(await usuariosUpdatePage.getUrlFotoPerfilInput()).to.eq('t8', 'Expected UrlFotoPerfil value to be equals to t8');

    await usuariosUpdatePage.save();
    expect(await usuariosUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await usuariosComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Usuarios', async () => {
    const nbButtonsBeforeDelete = await usuariosComponentsPage.countDeleteButtons();
    await usuariosComponentsPage.clickOnLastDeleteButton();

    usuariosDeleteDialog = new UsuariosDeleteDialog();
    expect(await usuariosDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Usuarios?');
    await usuariosDeleteDialog.clickOnConfirmButton();

    expect(await usuariosComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
