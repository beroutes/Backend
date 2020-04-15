import { element, by, ElementFinder } from 'protractor';

export class RutasComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-rutas div table .btn-danger'));
  title = element.all(by.css('jhi-rutas div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getText();
  }
}

export class RutasUpdatePage {
  pageTitle = element(by.id('jhi-rutas-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  tituloInput = element(by.id('field_titulo'));
  duracionInput = element(by.id('field_duracion'));
  temporadaInput = element(by.id('field_temporada'));
  presupuestoInput = element(by.id('field_presupuesto'));
  descripcionInput = element(by.id('field_descripcion'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setTituloInput(titulo: string): Promise<void> {
    await this.tituloInput.sendKeys(titulo);
  }

  async getTituloInput(): Promise<string> {
    return await this.tituloInput.getAttribute('value');
  }

  async setDuracionInput(duracion: string): Promise<void> {
    await this.duracionInput.sendKeys(duracion);
  }

  async getDuracionInput(): Promise<string> {
    return await this.duracionInput.getAttribute('value');
  }

  async setTemporadaInput(temporada: string): Promise<void> {
    await this.temporadaInput.sendKeys(temporada);
  }

  async getTemporadaInput(): Promise<string> {
    return await this.temporadaInput.getAttribute('value');
  }

  async setPresupuestoInput(presupuesto: string): Promise<void> {
    await this.presupuestoInput.sendKeys(presupuesto);
  }

  async getPresupuestoInput(): Promise<string> {
    return await this.presupuestoInput.getAttribute('value');
  }

  async setDescripcionInput(descripcion: string): Promise<void> {
    await this.descripcionInput.sendKeys(descripcion);
  }

  async getDescripcionInput(): Promise<string> {
    return await this.descripcionInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class RutasDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-rutas-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-rutas'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
